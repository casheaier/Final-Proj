package com.FinalTask.controller;

import com.FinalTask.dao.GoodsDao;
import com.FinalTask.dao.impl.GoodsDaoImpl;
import com.FinalTask.entity.Goods;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet({ "/cashier" })
public class CashierServlet extends HttpServlet {
    private static final Integer recordsPerPage = 18;
    private static Integer currentPage;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        currentPage = Integer.parseInt(request.getParameter("currentPage"));

        GoodsDao goodsDao = new GoodsDaoImpl();
        List<Goods> goodsList = goodsDao.findGoods(currentPage, recordsPerPage);

        request.setAttribute("goodsList2", goodsList);

        int rows = goodsDao.getNumberOfRows();

        int nOfPages = rows / recordsPerPage;

        if (nOfPages % recordsPerPage > 0) {

            nOfPages++;
        }

        request.setAttribute("noOfPages2", nOfPages);
        request.setAttribute("currentPage2", currentPage);
        request.setAttribute("recordsPerPage2", recordsPerPage);


        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/view/pages/cashier.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
