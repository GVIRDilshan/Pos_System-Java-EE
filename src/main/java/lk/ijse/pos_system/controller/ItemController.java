package lk.ijse.pos_system.controller;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import lk.ijse.pos_system.bo.BOFactory;
import lk.ijse.pos_system.bo.custom.ItemBO;
import lk.ijse.pos_system.dto.ItemDTO;
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

@WebServlet(name = "item_servlet", urlPatterns = "/item_servlet")
public class ItemController extends HttpServlet {
    private ItemBO itemBO = (ItemBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.ITEM);
    private final Logger logger = LoggerFactory.getLogger("lk.ijse.pos_system.custom");
    private Jsonb jsonb = JsonbBuilder.create();
    private ResponseDTO responseDTO = new ResponseDTO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("get");

        String iCode = req.getParameter("iCode");
        resp.setContentType("application/json");

        if (iCode != null) {
            try {
                ItemDTO itemDTO = itemBO.searchItem(iCode);

                responseDTO.setCode(HttpServletResponse.SC_OK);
                responseDTO.setMessage("Success");
                responseDTO.setContent(itemDTO);

                logger.info("Item Fetched!");

            } catch (Exception e) {
                resp.setStatus(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
//                e.printStackTrace();

                responseDTO.setCode(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
                responseDTO.setMessage("Can't find item");
                responseDTO.setContent(null);

                logger.error("Item not found");
            }
        } else {
            try {
                List<ItemDTO> allItems = itemBO.getAllItems();

                responseDTO.setCode(HttpServletResponse.SC_OK);
                responseDTO.setMessage("Success");
                responseDTO.setContent(allItems);

                logger.info("Items Fetched!");

            } catch (Exception e) {
                resp.setStatus(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
//                e.printStackTrace();

                responseDTO.setCode(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
                responseDTO.setMessage("Error");
                responseDTO.setContent(null);

                logger.error("Items not found");
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

        ItemDTO itemDTO = jsonb.fromJson(req.getReader(), ItemDTO.class);
        try {
            itemBO.addItem(itemDTO);

            responseDTO.setCode(HttpServletResponse.SC_OK);
            responseDTO.setMessage("Success");
            responseDTO.setContent(itemDTO);

            logger.info("Item Saved!");

        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
//            e.printStackTrace();

            responseDTO.setCode(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
            responseDTO.setMessage("Check duplicate item codes");
            responseDTO.setContent(itemDTO);

            logger.error("Item not saved!");
        }

        resp.getWriter().write(jsonb.toJson(responseDTO));

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("put");

        if (req.getContentType() == null || !req.getContentType().toLowerCase().startsWith("application/json")) {
            resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
            return;
        }
        resp.setContentType("application/json");

        ItemDTO itemDTO = jsonb.fromJson(req.getReader(), ItemDTO.class);
        try {
            itemBO.updateItem(itemDTO);

            responseDTO.setCode(HttpServletResponse.SC_OK);
            responseDTO.setMessage("Success");
            responseDTO.setContent(itemDTO);

            logger.info("Item Updated!");

        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
//            e.printStackTrace();

            responseDTO.setCode(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
            responseDTO.setMessage("Check item code");
            responseDTO.setContent(itemDTO);

            logger.error("Item not updated!");

        }

        resp.getWriter().write(jsonb.toJson(responseDTO));

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("delete");

        String iCode = req.getParameter("iCode");

        if (iCode == null) {
            resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
            return;
        }
        resp.setContentType("application/json");

        try {
            itemBO.deleteItem(iCode);

            responseDTO.setCode(HttpServletResponse.SC_OK);
            responseDTO.setMessage("Success");
            responseDTO.setContent(null);

            logger.info("Item Deleted!");
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
//            e.printStackTrace();

            responseDTO.setCode(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
            responseDTO.setMessage("Check i Code");
            responseDTO.setContent(null);

            logger.error("Item not deleted!");

        }

        resp.getWriter().write(jsonb.toJson(responseDTO));

    }

}
