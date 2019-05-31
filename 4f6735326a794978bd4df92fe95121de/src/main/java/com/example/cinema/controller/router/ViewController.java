package com.example.cinema.controller.router;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author deng
 * @date 2019/03/11
 */
@Controller
public class ViewController {
    @RequestMapping(value = "/index")
    public String getIndex() {
        return "index";
    }

    @RequestMapping(value = "/signUp")
    public String getSignUp() {
        return "signUp";
    }

    @RequestMapping(value = "/admin/vipInfo/manage")
    public String getAdminVIPInfoManage() {
        return "adminVIPInfoManage";
    }

    @RequestMapping(value = "/ticketSeller/movie/manage")
    public String getTicketSellerMovieManage() {
        return "ticketSellerMovieManage";
    }

    @RequestMapping(value = "/ticketSeller/session/manage")
    public String getTicketSellerSessionManage() {
        return "ticketSellerScheduleManage";
    }

    @RequestMapping(value = "/ticketSeller/cinema/manage")
    public String getTicketSellerCinemaManage() {
        return "adminCinemaManage";
    }

    @RequestMapping(value = "/ticketSeller/promotion/manage")
    public String getTicketSellerPromotionManage() {
        return "ticketSellerPromotionManage";
    }



    @RequestMapping(value = "/ticketSeller/cinema/statistic")
    public String getTicketSellerCinemaStatistic() {
        return "ticketSellerCinemaStatistic";
    }

    @RequestMapping(value = "/ticketSeller/movieDetail")
    public String getTicketSellerMovieDetail(@RequestParam int id) { return "ticketSellerMovieDetail"; }

    @RequestMapping(value = "/user/home")
    public String getUserHome() {
        return "userHome";
    }

    @RequestMapping(value = "/user/buy")
    public String getUserBuy() {
        return "userBuy";
    }

    @RequestMapping(value = "/user/movieDetail")
    public String getUserMovieDetail(@RequestParam int id) {
        return "userMovieDetail";
    }

    @RequestMapping(value = "/user/movieDetail/buy")
    public String getUserMovieBuy(@RequestParam int id) {
        return "userMovieBuy";
    }

    @RequestMapping(value = "/user/movie")
    public String getUserMovie() {
        return "userMovie";
    }

    @RequestMapping(value = "/user/member")
    public String getUserMember() {
        return "userMember";
    }
}
