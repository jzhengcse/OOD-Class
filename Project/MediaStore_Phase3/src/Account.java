
/**
 * Account contains variety methods to simulate a real account
 * @author jiz5118
 *
 */
public abstract class Account {
	protected String ID; //account id
	protected String name; //name of account holder
	protected String address; //short address of account holder
	
	
	/**
	 * default constructor of Account class
	 */
	public Account() {
		ID=""; //set ID to empty string
		name=""; //set name to empty string
		address=""; //set address to empty string
	}
	
	/**
	 * initial constructor for Account class
	 * @param ID
	 * @param name
	 * @param address
	 */
	public Account(String ID, String name, String address) {
		this.ID=ID; //set Id
		this.name=name; //set name
		this.address=address; //set address
		
	}
	

	

}
