package com.nrlm.cbo.model.response;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.List;

public class AssignedLocation implements Serializable {

    public static AssignedLocation jsonToJava(String jsonString) {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, AssignedLocation.class);
    }

    /*{"status":1,"error":{"code":"E200","message":"Success"},"clientId":"n{j5Y[<!Ps*HWCWg ","endPointUrl":"blockGpVill",
    "data":[{"block_name":"AMBER","block_code":"2712010",
    "gp_details":[{"gp_code":"2712010015","gp_name":"BILOCHI",
    "village_details":[{"village_name":"BILOCHI","village_code":"2712010015001"}]}]}]}*/

    private int status;
    private Error error;
    private String clientId;
    private String endPointUrl;
    private List<AssignedLocationData> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getEndPointUrl() {
        return endPointUrl;
    }

    public void setEndPointUrl(String endPointUrl) {
        this.endPointUrl = endPointUrl;
    }

    public List<AssignedLocationData> getData() {
        return data;
    }

    public void setData(List<AssignedLocationData> data) {
        this.data = data;
    }

    public class AssignedLocationData implements Serializable{
        private String block_name;
        private String block_code;
        private List<GpDetails> gp_details;

        public String getBlock_name() {
            return block_name;
        }

        public void setBlock_name(String block_name) {
            this.block_name = block_name;
        }

        public String getBlock_code() {
            return block_code;
        }

        public void setBlock_code(String block_code) {
            this.block_code = block_code;
        }

        public List<GpDetails> getGp_details() {
            return gp_details;
        }

        public void setGp_details(List<GpDetails> gp_details) {
            this.gp_details = gp_details;
        }
    }

    public class GpDetails implements Serializable{
        private String gp_code;
        private String gp_name;
        private List<VillageDetails> village_details;

        public String getGp_code() {
            return gp_code;
        }

        public void setGp_code(String gp_code) {
            this.gp_code = gp_code;
        }

        public String getGp_name() {
            return gp_name;
        }

        public void setGp_name(String gp_name) {
            this.gp_name = gp_name;
        }

        public List<VillageDetails> getVillage_details() {
            return village_details;
        }

        public void setVillage_details(List<VillageDetails> village_details) {
            this.village_details = village_details;
        }
    }

    public class VillageDetails implements Serializable{
        private String village_code;
        private String village_name;

        public String getVillage_code() {
            return village_code;
        }

        public void setVillage_code(String village_code) {
            this.village_code = village_code;
        }

        public String getVillage_name() {
            return village_name;
        }

        public void setVillage_name(String village_name) {
            this.village_name = village_name;
        }
    }

}
