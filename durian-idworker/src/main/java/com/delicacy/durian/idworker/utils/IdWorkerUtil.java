package com.delicacy.durian.idworker.utils;


import com.delicacy.durian.idworker.idworker.IdWorker;

public class IdWorkerUtil {

    static private IdWorker idWorker;

    public static void setIdWorker(IdWorker idWorker) {
        IdWorkerUtil.idWorker = idWorker;
    }

    public static long nextId() {
        return idWorker.nextId();
    }
}