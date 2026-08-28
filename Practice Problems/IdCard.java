class IdCard {
    String name;
    int bookIssued;
    IdCard(String name, int bookIssued) {
        this.name = name;
        this.bookIssued = bookIssued;
    }

    public static void main(String[] args)
    {
        IdCard ravi =new IdCard("Ravi",0);
        IdCard duplicate=ravi;
        duplicate.bookIssued=3;
        System.out.println("Ravi's booksIssues(via first variable):"+ravi.bookIssued);
        IdCard separate = new IdCard("Ravi",0);
        System.out.println("duplicate==ravi   "+(duplicate==ravi));
        System.out.println("seperate==ravi  "+(separate==ravi));
    }
}
