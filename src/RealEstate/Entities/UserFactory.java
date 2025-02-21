package RealEstate.Entities;

public class UserFactory {

    private Person person;

    public UserFactory()
    {
        person = null;
    }

    public Person createUserType(String name, int i)
    {
        switch (i)
        {
            case 1:
                return new Agent(name);
            case 2:
                return new Buyer(name);
            case 3:
                return new Seller(name);
            default:
        }

        return null;
    }
}
