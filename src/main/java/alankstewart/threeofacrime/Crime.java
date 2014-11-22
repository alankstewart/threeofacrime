package alankstewart.threeofacrime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Crime {

    private final List<Suspect> suspects = new ArrayList<>();

    public Crime() {
        final List<Suspect> suspects = Stream.of(Suspect.values()).collect(toList());
        Collections.shuffle(suspects);
        this.suspects.addAll(suspects.stream().limit(3).collect(toList()));
    }

    public List<Suspect> getSuspects() {
        return Collections.unmodifiableList(suspects);
    }
}
