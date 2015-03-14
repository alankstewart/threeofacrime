package alankstewart.threeofacrime.web;

import alankstewart.threeofacrime.model.Suspect;
import alankstewart.threeofacrime.model.SuspectCard;
import alankstewart.threeofacrime.model.ThreeOfACrime;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonStructure;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collector;

/**
 * Created by alanstewart on 4/03/15.
 */
public class GameServlet extends HttpServlet {

    private static final String GAME_KEY = "game";
    private static final String NEW_ROUND_KEY = "startNewRound";

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        final HttpSession session = req.getSession();
        if (Boolean.parseBoolean(req.getParameter(NEW_ROUND_KEY))) {
            session.setAttribute(NEW_ROUND_KEY, Boolean.TRUE);
        } else {
            session.removeAttribute(GAME_KEY);
        }
    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        final String[] selectedSuspects = req.getParameterValues("selectedSuspects[]");
        final SuspectCard suspectCard = SuspectCard.of(selectedSuspects[0], selectedSuspects[1], selectedSuspects[2]);

        final int matches;
        try {
            matches = Integer.parseInt(req.getParameter("matches"));
        } catch (final NumberFormatException e) {
            throw new ServletException(e);
        }

        final ThreeOfACrime threeOfACrime = getGame(req.getSession());
        threeOfACrime.matchSuspects(suspectCard, matches);
        Json.createWriter(resp.getOutputStream()).write(threeOfACrime.getSuspectCards()
                .stream()
                .map(s -> toJson(s.getSuspects()))
                .collect(Collector.of(Json::createArrayBuilder, JsonArrayBuilder::add, (left, right) -> {
                    left.add(right);
                    return left;
                })).build());

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getOutputStream().flush();
    }

    private ThreeOfACrime getGame(final HttpSession session) {
        ThreeOfACrime threeOfACrime = (ThreeOfACrime) session.getAttribute(GAME_KEY);
        if (threeOfACrime == null) {
            threeOfACrime = new ThreeOfACrime();
            session.setAttribute(GAME_KEY, threeOfACrime);
        }
        if (session.getAttribute(NEW_ROUND_KEY) != null) {
            threeOfACrime.startNewRound();
            session.removeAttribute(NEW_ROUND_KEY);
        }
        return threeOfACrime;
    }

    private JsonStructure toJson(final List<Suspect> suspects) {
        return Json.createArrayBuilder()
                .add(suspects.get(0).getDisplayName())
                .add(suspects.get(1).getDisplayName())
                .add(suspects.get(2).getDisplayName())
                .build();
    }
}