package alankstewart.threeofacrime;

import org.junit.Test;

public class CrimeTest {

    @Test
    public void shouldGenerateRandomPerpetrators() {
//        ICombinatoricsVector<Suspect> initialVector = Factory.createVector(Arrays.asList(Suspect.values()));
//        Generator<Suspect> gen = Factory.createSimpleCombinationGenerator(initialVector, 3);
//      //  gen.forEach(System.out::println);
      //  System.out.println(gen.getNumberOfGeneratedObjects());
  //      final List<ICombinatoricsVector<Suspect>> allObjects = gen.generateAllObjects();
   //   allObjects.forEach(System.out::println);
    //    System.out.println(gen.getNumberOfGeneratedObjects());

        SuspectCardGenerator generator = new SuspectCardGenerator();
     //   List<SuspectCard> suspectCards = generator.
     //   System.out.println(generator.getNumberOfGeneratedObjects());
       generator.getSuspectCards().forEach(System.out::println);

    }
}
