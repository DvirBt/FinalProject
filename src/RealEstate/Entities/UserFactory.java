package RealEstate.Entities;

public class UserFactory {

    private Person person;

    public UserFactory()
    {
        person = null;
    }

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
