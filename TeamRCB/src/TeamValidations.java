import org.testng.Assert;
import org.testng.annotations.Test;

import Files.Payload;
import io.restassured.path.json.JsonPath;

public class TeamValidations {
	
	JsonPath js=new JsonPath(Payload.TeamRCB());
	int count=	js.getInt("player.size()");
	@Test
	public void NumberOfForiegnPlayers()
	{
		int requiedForiegnPlayersLimit = 4;
		int foriegnPlayersCount = 0;
		for(int i=0;i<count;i++)
		{
			String playerCountry = js.getString("player["+i+"].country");
			if(!playerCountry.contentEquals("India"))
			{	
				foriegnPlayersCount++;
			}
		}
		Assert.assertEquals(foriegnPlayersCount, requiedForiegnPlayersLimit);
		
	}
	@Test
	public void OneWicketKeeper() 
	{
		int minWkLimit = 1;
		int wkCount = 0;
		for(int i= 0;i<count;i++)
		{
			String playerRole = js.getString("player["+i+"].role");
			if(playerRole.equalsIgnoreCase("Wicket-keeper"))
			{
				wkCount++;
			}
		}
		if(wkCount>=1)
		{
			Assert.assertTrue(true);
		}
		else
		{
			Assert.assertTrue(false);
		}
	}
}
