package filter.interface_adapter;

import filter.use_case.FilterOutput;
import filter.use_case.FilterOutputBoundary;
import filter.view.FilterView;

public class FilterPresenter implements FilterOutputBoundary {
    private FilterViewModel filterViewModel;

    public FilterPresenter(FilterViewModel filterViewModel) {
        this.filterViewModel = filterViewModel;
    }

    public void setFilterViewModel(FilterOutput filterOutput) {
        filterViewModel.setFilterOutput(filterOutput.getRecipeList1());
    }

}
