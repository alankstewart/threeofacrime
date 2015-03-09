package alankstewart.threeofacrime.web;

import alankstewart.threeofacrime.model.SuspectCard;
import alankstewart.threeofacrime.model.ThreeOfACrime;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.stream.JsonGenerator;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collector;

/**
 * Created by alanstewart on 4/03/15.
 */
public class GameServlet extends HttpServlet {

    private ThreeOfACrime threeOfACrime;

    @Override
    public void init() throws ServletException {
        threeOfACrime = new ThreeOfACrime();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        init();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] suspects = req.getParameterValues("suspects[]");
        SuspectCard suspectCard = SuspectCard.of(suspects[0], suspects[1], suspects[2]);
        int matches = Integer.parseInt(req.getParameter("matches"));
        threeOfACrime.matchSuspects(suspectCard, matches);
        Json.createWriter(resp.getOutputStream()).write(threeOfACrime.getSuspectCards().stream().map(SuspectCard::toJson)
                .collect(Collector.of(Json::createArrayBuilder, JsonArrayBuilder::add, (left, right) -> {
                    left.add(right);
                    return left;
                })).build());
        resp.getOutputStream().flush();
    }
}
