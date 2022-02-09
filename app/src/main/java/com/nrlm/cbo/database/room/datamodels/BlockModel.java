package com.nrlm.cbo.database.room.datamodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.nrlm.cbo.database.room.entities.BlockEntity;
import com.nrlm.cbo.database.room.repositories.BlockRepo;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class BlockModel extends AndroidViewModel {
    private BlockRepo blockRepo;

    public BlockModel(@NonNull Application application) {
        super(application);
        blockRepo=new BlockRepo(application);
    }
    public void insertAll(BlockEntity blockEntity){
        blockRepo.insertAll(blockEntity);
    }

    public List<BlockEntity> getAllBlock() throws ExecutionException, InterruptedException {
       return blockRepo.getAllBlock();
    }
}
