package use_case.filter;


public interface FilterInputBoundary {
    void filterRecipes(FilterInput input);


    public StringBuilder getResults();
}
