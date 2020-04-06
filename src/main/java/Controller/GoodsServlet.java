package Controller;

import Bean.Goods;
import Bean.Result;
import Service.GoodsService;
import Service.GoodsServiceImpl;
import Service.SpecService;
import Service.SpecServiceImpl;
import Utils.FileUploadUtils;
import Utils.HttpUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/api/admin/goods/*")
public class GoodsServlet extends HttpServlet {
    private GoodsService goodsService = new GoodsServiceImpl();
    private SpecService specService = new SpecServiceImpl();
    private Gson gson = new Gson();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String sector = requestURI.replace("/api/admin/goods/", "");
        switch (sector){
            case "imgUpload":
                imgUpload(request,response);
                break;
            case "addGoods":
                addGoods(request,response);
                break;
            case "updateGoods":
                updateGoods(request,response);
                break;
        }
    }

    private void updateGoods(HttpServletRequest request, HttpServletResponse response) {
    }

    private void addGoods(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttpUtils.getRequestBody(request);
        Goods goods = gson.fromJson(requestBody, Goods.class);
        int status = goodsService.addGoods(goods);
        Result result = new Result();
        if (status == 0){
            result.setCode(0);
        }else if (status == 500){
            result.setCode(500);
            result.setMessage("Add failed!");
        }
        response.getWriter().println(gson.toJson(result,Result.class));
    }

    private void imgUpload(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String domain = (String) request.getServletContext().getAttribute("domain");
        Map<String, Object> map = FileUploadUtils.getFileParams(request);
        //  localhost:8084/upload/......jpeg
        String file = (String) map.get("file");
        // 前后端跨域时，最好知名全路径，包含主机名等
        file = domain + file;
        Result result = new Result();
        result.setCode(0);
        result.setData(file);

        response.getWriter().println(gson.toJson(result));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String sector = requestURI.replace("/api/admin/goods/", "");
        switch (sector){
            case "getGoodsByType":
                getGoodsByType(request,response);
                break;
            case "deleteGoods":
                deleteGoods(request,response);
                break;
            case "getGoodsInfo":
                getGoodsInfo(request,response);
                break;
        }
    }

    private void getGoodsInfo(HttpServletRequest request, HttpServletResponse response) {
    }

    private void deleteGoods(HttpServletRequest request, HttpServletResponse response) {
    }

    private void getGoodsByType(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int typeId = Integer.parseInt(request.getParameter("typeId"));
        Result result = new Result();
        result.setCode(0);
        result.setData(goodsService.getGoodsByType(typeId));
        response.getWriter().println(gson.toJson(result, Result.class));
    }
}
