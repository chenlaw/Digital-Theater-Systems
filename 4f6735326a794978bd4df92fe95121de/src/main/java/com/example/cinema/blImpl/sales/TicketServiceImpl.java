package com.example.cinema.blImpl.sales;

import com.example.cinema.bl.promotion.CouponService;
import com.example.cinema.bl.promotion.VIPService;
import com.example.cinema.bl.sales.TicketService;
import com.example.cinema.blImpl.management.hall.HallServiceForBl;
import com.example.cinema.blImpl.management.schedule.MovieServiceForBl;
import com.example.cinema.blImpl.management.schedule.ScheduleServiceForBl;
import com.example.cinema.data.management.ScheduleMapper;
import com.example.cinema.data.promotion.ActivityMapper;
import com.example.cinema.data.promotion.CouponMapper;
import com.example.cinema.data.promotion.VIPCardMapper;
import com.example.cinema.data.sales.TicketMapper;
import com.example.cinema.data.sales.WithdrawMapper;
import com.example.cinema.po.*;
import com.example.cinema.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by liying on 2019/4/16.
 */
@Service
public class  TicketServiceImpl implements TicketService {

    @Autowired
    TicketMapper ticketMapper;
    @Autowired
    ScheduleServiceForBl scheduleService;
    @Autowired
    HallServiceForBl hallService;
    @Autowired
    CouponService couponService;
    @Autowired
    CouponMapper couponMapper;
    @Autowired
    VIPCardMapper vipCardMapper;
    @Autowired
    ActivityMapper activityMapper;
    @Autowired
    ScheduleMapper scheduleMapper;
    @Autowired
    WithdrawMapper withdrawMapper;

    @Override
    @Transactional
    public ResponseVO addTicket(TicketForm ticketForm) {
        /**
         * Created by wph on 2019/5/8.
         */
        try {
            List<Ticket> ticketList = new ArrayList<Ticket>();
            List<TicketVO> ticketVOList = new ArrayList<TicketVO>();
            List<SeatForm> seatList = ticketForm.getSeats();
            double eachTicketPrice = 0;
            double total = 0;
            for (int i = 0; i<seatList.size();i++) {
                Ticket ticket = new Ticket();
                ticket.setUserId(ticketForm.getUserId());
                ticket.setScheduleId(ticketForm.getScheduleId());
                ticket.setRowIndex(seatList.get(i).getRowIndex());
                ticket.setColumnIndex(seatList.get(i).getColumnIndex());
                ticket.setState(0);
                ticket.setTime(new Timestamp(System.currentTimeMillis()));
                ticketList.add(ticket);
                ticketMapper.insertTicket(ticket);
                ticketVOList.add((ticketMapper.selectTicketByScheduleIdAndSeat(ticketForm.getScheduleId(),seatList.get(i).getColumnIndex(),seatList.get(i).getRowIndex())).getVO());
                eachTicketPrice = scheduleMapper.selectScheduleById(ticket.getScheduleId()).getFare();
                total = total + eachTicketPrice;
            }
//            if (ticketList.size()==1) {
//                ticketMapper.insertTicket(ticketList.get(0));
//            }
//            else {
//                ticketMapper.insertTickets(ticketList);
//            }
            TicketWithCouponVO ticketWithCouponVO = new TicketWithCouponVO();
            ticketWithCouponVO.setTicketVOList(ticketVOList);
            ticketWithCouponVO.setActivities(activityMapper.selectActivities());
            ticketWithCouponVO.setCoupons(couponMapper.selectCouponByUser(ticketVOList.get(0).getUserId()));
            ticketWithCouponVO.setTotal(total);
            return ResponseVO.buildSuccess(ticketWithCouponVO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }


    @Override
    @Transactional
    public ResponseVO completeTicket(TicketInfoVO ticketInfoVO) {
        List<Integer> id=ticketInfoVO.getTicketId();
        int couponId=ticketInfoVO.getCouponId();
        int movieId=0;
        try{
            double totalPrice = 0;
            int userID = 0;
            for(int i=0;i<id.size();i++){
                Ticket ticket = ticketMapper.selectTicketById(id.get(i));
                totalPrice += scheduleMapper.selectScheduleById(ticket.getScheduleId()).getFare();
                userID = ticket.getUserId();
                movieId = scheduleMapper.selectScheduleById(ticket.getScheduleId()).getMovieId();
            }
            if(couponId!=0){
                Coupon coupon = couponMapper.selectById(couponId);
                double targetAmount = coupon.getTargetAmount();
                if(totalPrice<targetAmount){
                    return ResponseVO.buildFailure("你没花够钱，还想用这张优惠券，真是个蔡徐坤！");
                }
                else{
                    couponMapper.deleteCouponUser(couponId,userID);
                }
            }
            List<Activity> activities=activityMapper.selectActivitiesByMovie(movieId);
            if(activities.size()!=0){
                Coupon bestCoupon=activities.get(0).getCoupon();
                for (int i=0;i<activities.size();i++){
                    Coupon coupon = activities.get(i).getCoupon();
                    if(bestCoupon.getTargetAmount()<=coupon.getTargetAmount()&&totalPrice>=coupon.getTargetAmount()){
                        bestCoupon=coupon;
                    }
                }
                couponMapper.insertCouponUser(bestCoupon.getId(),userID);
            }
            for(int i=0;i<id.size();i++){
                ticketMapper.updateTicketState(id.get(i),1);
            }
            return ResponseVO.buildSuccess();
        }catch(Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getBySchedule(int scheduleId) {
        try {
            List<Ticket> tickets = ticketMapper.selectTicketsBySchedule(scheduleId);
            ScheduleItem schedule=scheduleService.getScheduleItemById(scheduleId);
            Hall hall=hallService.getHallById(schedule.getHallId());
            int[][] seats=new int[hall.getRow()][hall.getColumn()];
            tickets.stream().forEach(ticket -> {
                seats[ticket.getRowIndex()][ticket.getColumnIndex()]=1;
            });
            ScheduleWithSeatVO scheduleWithSeatVO=new ScheduleWithSeatVO();
            scheduleWithSeatVO.setScheduleItem(schedule);
            scheduleWithSeatVO.setSeats(seats);
            return ResponseVO.buildSuccess(scheduleWithSeatVO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getTicketByUser(int userId) {
        try {
            return ResponseVO.buildSuccess(ticketList2TicketWithScheduleVOList(ticketMapper.selectTicketByUser(userId)));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    @Transactional
    public ResponseVO completeByVIPCard(TicketInfoVO ticketInfoVO) {
        List<Integer> id = ticketInfoVO.getTicketId();
        int couponId = ticketInfoVO.getCouponId();
        int movieId = 0;
        try{
            double totalPrice = 0;
            int userID = 0;
            for(int i=0;i<id.size();i++){
                Ticket ticket = ticketMapper.selectTicketById(id.get(i));
                totalPrice += scheduleMapper.selectScheduleById(ticket.getScheduleId()).getFare();
                userID = ticket.getUserId();
                movieId = scheduleMapper.selectScheduleById(ticket.getScheduleId()).getMovieId();
            }
            double totalPriceAfterCoupon = totalPrice;
            if(couponId!=0){
                Coupon coupon = couponMapper.selectById(couponId);
                double targetAmount = coupon.getTargetAmount();
                if(totalPrice<targetAmount){
                    return ResponseVO.buildFailure("你没花够钱，还想用这张优惠券，真是个蔡徐坤！");
                }
                else{
                    totalPriceAfterCoupon -= coupon.getDiscountAmount();
                    couponMapper.deleteCouponUser(couponId,userID);
                }
            }
            List<Activity> activities=activityMapper.selectActivitiesByMovie(movieId);
            if(activities.size()!=0){
                Coupon bestCoupon=activities.get(0).getCoupon();
                for (int i=0;i<activities.size();i++){
                    Coupon coupon = activities.get(i).getCoupon();
                    if(bestCoupon.getTargetAmount()<=coupon.getTargetAmount()&&totalPrice>=coupon.getTargetAmount()){
                        bestCoupon=coupon;
                    }
                }
                couponMapper.insertCouponUser(bestCoupon.getId(),userID);
            }
            VIPCard vipCard=vipCardMapper.selectCardByUserId(userID);
            double balance = vipCard.getBalance()-totalPriceAfterCoupon;
            if(balance<0){
                return ResponseVO.buildFailure("余额不足");
            }
            else{
                for(int i=0;i<id.size();i++){
                    ticketMapper.updateTicketState(id.get(i),1);
                }
                vipCardMapper.updateCardBalance(vipCard.getId(),balance);
            }
            return ResponseVO.buildSuccess();
        }catch(Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }

    }

    @Override
    public ResponseVO cancelTicket(List<Integer> id) {
    	/**
    	 * Created by wph on 2019/5/8.
    	 */
        try {
        	for (int i = 0; i < id.size();i++) {
        		ticketMapper.updateTicketState(id.get(i), 2);
        	}
        	return ResponseVO.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    private List<TicketWithScheduleVO> ticketList2TicketWithScheduleVOList(List<Ticket> ticketList){
        List<TicketWithScheduleVO> TicketWithScheduleVOList = new ArrayList<>();
        for(Ticket ticket : ticketList){
            TicketWithScheduleVOList.add(ticket.getWithScheduleVO(scheduleMapper.selectScheduleById(ticket.getScheduleId())));
        }
        Collections.reverse(TicketWithScheduleVOList);
        return TicketWithScheduleVOList;
    }

    @Override
    public ResponseVO withdrawTicket(int id){
        Ticket ticket = ticketMapper.selectTicketById(id);
        ScheduleItem scheduleItem = scheduleMapper.selectScheduleById(ticket.getScheduleId());
        //Date startTime=scheduleItem.getStartTime();
        double fare = scheduleItem.getFare();
        WithdrawInfo withdrawInfo = withdrawMapper.selectWithdrawInfoByScheduleId(ticket.getScheduleId());
        Date closeTime=withdrawInfo.getCloseTime();
        Date currentTime = new Date();
        if(currentTime.after(closeTime)){
            return ResponseVO.buildFailure("已超过退票时间！");
        }
        else{
            ticketMapper.updateTicketState(id,2);
            int userId=ticket.getUserId();
            VIPCard vipCard=vipCardMapper.selectCardByUserId(userId);
            if(vipCard!=null){
                double balance=vipCard.getBalance();
                double discount=withdrawInfo.getDiscount();
                balance+=discount*fare;
                vipCardMapper.updateCardBalance(vipCard.getId(),balance);
            }
            return ResponseVO.buildSuccess();
        }
    }

}
