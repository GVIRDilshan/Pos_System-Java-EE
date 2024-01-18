package lk.ijse.pos_system.controller;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import lk.ijse.pos_system.bo.BOFactory;
import lk.ijse.pos_system.bo.custom.OrderBO;
import lk.ijse.pos_system.dto.OrderDTO;
import lk.ijse.pos_system.dto.ResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "order_servlet", urlPatterns = "/order_servlet")
public class OrderController extends HttpServlet {
    private OrderBO orderBO = (OrderBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.ORDER);
    private Jsonb jsonb = JsonbBuilder.create();
    private final Logger logger = LoggerFactory.getLogger("lk.ijse.pos_system.custom");
    private ResponseDTO responseDTO = new ResponseDTO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("Order_get");

        String oId = req.getParameter("oId");
        resp.setContentType("application/json");

        if (oId != null) {
            try {
                OrderDTO orderDTO = orderBO.searchOrder(oId);

                responseDTO.setCode(HttpServletResponse.SC_OK);
                responseDTO.setMessage("Success");
                responseDTO.setContent(orderDTO);

                logger.info("Order Fetched!");

            } catch (Exception e) {
                resp.setStatus(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
//                e.printStackTrace();

                responseDTO.setCode(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
                responseDTO.setMessage("Check order id");
                responseDTO.setContent(null);

                logger.error("Can't find order");

            }

        } else {
            try {
                List<OrderDTO> allOrders = orderBO.getAllOrders();

                responseDTO.setCode(HttpServletResponse.SC_OK);
                responseDTO.setMessage("Success");
                responseDTO.setContent(allOrders);

                logger.info("Orders Fetched!");

            } catch (Exception e) {
                resp.setStatus(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
//                e.printStackTrace();

                responseDTO.setCode(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
                responseDTO.setMessage("Can't find orders");
                responseDTO.setContent(null);

                logger.error("Can't find orders!");

            }

        }
        resp.getWriter().write(jsonb.toJson(responseDTO));

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("post");

        if (req.getContentType() == null || !req.getContentType().toLowerCase().startsWith("application/json")) {
            resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
            return;
        }
        resp.setContentType("application/json");

        OrderDTO orderDTO = jsonb.fromJson(req.getReader(), OrderDTO.class);
        try {
            orderBO.addOrder(orderDTO);

            responseDTO.setCode(HttpServletResponse.SC_OK);
            responseDTO.setMessage("Success");
            responseDTO.setContent(orderDTO);

            logger.info("Order placed!");

        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
//            e.printStackTrace();

            responseDTO.setCode(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
            responseDTO.setMessage("Check duplicate order ids");
            responseDTO.setContent(orderDTO);

            logger.info("Order not placed!");

        }
        resp.getWriter().write(jsonb.toJson(responseDTO));

    }

}
