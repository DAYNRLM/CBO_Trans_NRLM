package com.nrlm.cbo.database.room.repositories;

import android.app.Application;

import androidx.annotation.NonNull;

import com.nrlm.cbo.database.ShgTrans;
import com.nrlm.cbo.database.room.daos.BlockDao;
import com.nrlm.cbo.database.room.entities.BlockEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class BlockRepo {
    private BlockDao blockDao;

    public BlockRepo(@NonNull Application application){
        ShgTrans shgTrans=ShgTrans.getDatabase(application);
        blockDao=shgTrans.blockDao();
    }

    public void insertAll(BlockEntity blockEntity){
        ShgTrans.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                blockDao.insertAll(blockEntity);
            }
        });
    }

  /*  public List<BlockEntity> getAllBlock(){
        final List<BlockEntity>[] allBlock;
        ShgTrans.databaseWriteExecutor.execute(() ->
                        allBlock[0] = blockDao.getAllBlock()
                );

    }*/

    public List<BlockEntity> getAllBlock() throws ExecutionException, InterruptedException {

        Callable<List<BlockEntity>> callable = new Callable<List<BlockEntity>>() {
            @Override
            public List<BlockEntity> call() throws Exception {
                return blockDao.getAllBlock();
            }
        };

        Future<List<BlockEntity>> future = Executors.newSingleThreadExecutor().submit(callable);

        return future.get();
    }
}
