package auctionhouse;

import ail.util.AILexception;

/**
 *
 * @author asj
 */
public class AHwithExistingAuction extends AuctionHouse {

	@Override
	public void initialise() {
		try {
			register("sally", "address", "account");
			verify("mike", "sally");
			startAuction("lamp", "sally", 5, 10);
		} catch (AILexception ex) {
			throw new RuntimeException(ex);
		}
	}
	
}
