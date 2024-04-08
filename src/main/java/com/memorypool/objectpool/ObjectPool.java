package com.memorypool.objectpool;

import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * packageName    : com.memorypool.objectpool
 * fileName       : ObjectPool
 * author         : GAMJA
 * date           : 2024/04/08
 * description    : 오브젝트 풀 클래스
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/04/08        GAMJA       최초 생성
 */
@Getter
@Setter
public class ObjectPool<T> {

    private List<T> poolList = new ArrayList<>();
    private Class<T> classObj;
    private int maxPoolSize = 10;

    public ObjectPool(Class<T> classObj){
        this.classObj = classObj;
    }

    //poolist 에 객체 생성하여 담기
    public T borrowObject() throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        if(poolList.size() > maxPoolSize) {
            throw new PoolsizeException("pool size over");
        }
        poolList.add(classObj.getConstructor().newInstance());
        return classObj.getConstructor().newInstance();
    }

    //객체 반환하기
    public boolean returnObject( T obj) {
        return poolList.remove(obj);
    }

    //poolList 안에 있는 객체인지 체크
    public boolean isValidateObject(T obj) {
        for( T data : poolList){
            if(data != null && data.equals(obj)){
                return true;
            }
        }
        return false;
    }

    //객체 를 꺼낼 경우 리스트에서 반환
    public T getObject( ){
        if(poolList.size() < 1){
            return null;
        }
        T instance = poolList.get(poolList.size() - 1);
        returnObject(instance);
        return instance;
    }

    static class PoolsizeException extends RuntimeException {
        public PoolsizeException(String message) {
            super(message);
        }
    }

}
