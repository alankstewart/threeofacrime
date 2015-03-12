package alankstewart.threeofacrime.web;

import alankstewart.threeofacrime.model.Suspect;
import alankstewart.threeofacrime.model.SuspectCard;
import alankstewart.threeofacrime.model.ThreeOfACrime;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

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
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        init();
    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        final String[] selectedSuspects = req.getParameterValues("selectedSuspects[]");
        final SuspectCard suspectCard = SuspectCard.of(selectedSuspects[0], selectedSuspects[1], selectedSuspects[2]);
        final int matches = Integer.parseInt(req.getParameter("matches"));

        threeOfACrime.matchSuspects(suspectCard, matches);
        final List<SuspectCard> suspectCards = threeOfACrime.getSuspectCards();

        final Set<Suspect> uniqueSuspects = suspectCards.stream().flatMap(s -> s.getSuspects().stream()).collect(toSet());
        final List<Suspect> innocentSuspects = Arrays.stream(Suspect.values()).collect(toList())
                .stream()
                .filter(s -> !uniqueSuspects.contains(s))
                .collect(toList());

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        Json.createWriter(resp.getOutputStream()).write(Json.createObjectBuilder()
                .add("suspectCards", suspectCards.stream().map(SuspectCard::toJson)
                        .collect(Collector.of(Json::createArrayBuilder,
                                JsonArrayBuilder::add, (left, right) -> {
                                    left.add(right);
                                    return left;
                                })).build())
                .add("innocentSuspects", innocentSuspects.stream().map(Suspect::name)
                        .collect(Collector.of(Json::createArrayBuilder,
                                JsonArrayBuilder::add, (left, right) -> {
                                    left.add(right);
                                    return left;
                                })).build())
                .build());
        resp.getOutputStream().flush();
    }
}
