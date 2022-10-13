package com.myproject.offlinebudgettrackerappproject.model;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ItemSpendingViewModel extends ViewModel {

    private final MutableLiveData<Integer> selectedItem = new MutableLiveData<Integer>();

    public ItemSpendingViewModel(FragmentActivity activity) {
    }

    public ItemSpendingViewModel() {
    }

    public void setData(Integer item) {
        selectedItem.setValue(item);
    }

    public LiveData<Integer> getSelectedItem() {
        return selectedItem;
    }
}
