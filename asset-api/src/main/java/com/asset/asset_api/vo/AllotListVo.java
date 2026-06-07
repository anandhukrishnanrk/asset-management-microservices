package com.asset.asset_api.vo;


import java.util.List;
import com.asset.asset_api.entity.AssetStore;
import com.asset.asset_api.entity.HierarchySetup;
import com.asset.asset_api.entity.SeatUserAlloted;

public class AllotListVo {

    private Integer storeId;
    private Integer officeId;

    private List<AssetStore> assetStore;
    private List<HierarchySetup> assetOfficeList;
    private List<SeatUserAlloted> userList;

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Integer officeId) {
        this.officeId = officeId;
    }

    public List<AssetStore> getAssetStore() {
        return assetStore;
    }

    public void setAssetStore(List<AssetStore> assetStore) {
        this.assetStore = assetStore;
    }

    public List<HierarchySetup> getAssetOfficeList() {
        return assetOfficeList;
    }

    public void setAssetOfficeList(List<HierarchySetup> assetOfficeList) {
        this.assetOfficeList = assetOfficeList;
    }

    public List<SeatUserAlloted> getUserList() {
        return userList;
    }

    public void setUserList(List<SeatUserAlloted> userList) {
        this.userList = userList;
    }
}
