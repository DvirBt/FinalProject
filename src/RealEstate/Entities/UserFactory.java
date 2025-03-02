package RealEstate.Entities;

/**
 * This class represents a factory of the users of the system. It creates the correct instance based on the number of the role.
 */
public class UserFactory {

    public UserFactory()
    {
    }

    /**
     * This function create a user based on the given parameters.
     * @param firstName the first name of the user.
     * @param lastName the last name of the user.
     * @param i the number of the role
     * @return the relevant object based on the parameters. Returns null if an unrecognized role number detected.
     */
    public Person createUserType(String firstName, String lastName, int i)
    {
        switch (i)
        {
            case 1:
                return new Agent(firstName, lastName);
            case 2:
                return new Buyer(firstName, lastName);
            case 3:
                return new Seller(firstName,lastName);
            default:
        }

        return null;
    }
}
