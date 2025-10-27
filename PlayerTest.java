import org.junit.*;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PlayerTest {
	private Player player;
	private ArrayList<Card> discardPile;

	// runs before each test
	@Before
	public void setUp(){
		player = new Player("John");
		discardPile = new ArrayList<>();
	}

	//testing
	@Test
	public void testgetName(){
		assertEquals("John", player.getName());
  }

  @Test
  public void testgetHand(){
		// the hand is initially empty
	  assertTrue("players hand is empty",player.getHand().isEmpty());
  }

  @Test
  public void testhasZeroCard(){
		assertTrue("player has zero cards", player.hasZeroCard());
  }

	@Test
  public void testplayCard() {
	  player.getHand().add(new Card());
	  player.getHand().add(new Card());
	  player.getHand().add(new Card());
	  Card topcard = new Card();

	  // play the first card
	  boolean playing = player.playCard(0, topcard, discardPile);

	  assertNotNull(playing);
	  if (playing) {
		  assertEquals("players card is valid therefore hand size decreased by 1",2, player.getHand().size());
		  assertEquals("players card is valid therefore discard pile increase by 1",1, discardPile);
	  } else {
		  assertEquals("the players card is invalid there the hand size stays the same",3, player.getHand().size());
		  assertTrue("the pile is empty",discardPile.isEmpty());
	  }
  }

  @Test
  public void testplayCardInvalidIndex(){
		Card topCard = new Card();
		Card card = new Card();
		player.getHand().add(card);
		boolean result = player.playCard(3, topCard, discardPile);
		assertFalse("Invalid index should return False", result);
  }

  @Test
  public void testShowHand(){
		player.getHand().add(new Card());
		String printed = player.showHand();
		assertEquals(" ", printed);
  }


}
