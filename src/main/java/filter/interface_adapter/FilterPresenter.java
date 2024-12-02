package filter.interface_adapter;

import filter.use_case.FilterOutput;
import filter.use_case.FilterOutputBoundary;

/**
 * The FilterPresenter class is responsible for presenting the filter output.
 */
public class FilterPresenter implements FilterOutputBoundary {
    private final FilterViewModel filterViewModel;

    public FilterPresenter(FilterViewModel filterViewModel) {
        this.filterViewModel = filterViewModel;
    }

    /**
     * Sets the view model for the filter output.
     * @param filterOutput the view model for the filter output
     */
    public void setFilterViewModel(FilterOutput filterOutput) {
        filterViewModel.setFilterOutput(filterOutput.getRecipeList1());
    }

}
