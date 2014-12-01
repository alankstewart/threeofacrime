package alankstewart.threeofacrime;

import org.junit.Test;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static alankstewart.threeofacrime.Suspect.HUMPTY_BUMPTY;
import static alankstewart.threeofacrime.Suspect.NO_NECK_NICK;
import static alankstewart.threeofacrime.Suspect.PENCIL_TOP;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.junit.Assert.assertThat;

public class ThreeOfACrimeTest {

    @Test
    public void shouldStartGame() {
        ThreeOfACrime threeOfACrime = ThreeOfACrime.start();

    }

}
