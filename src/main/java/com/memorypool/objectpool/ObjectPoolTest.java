package com.memorypool.objectpool;

/**
 * packageName    : com.memorypool.objectpool
 * fileName       : main
 * author         : GAMJA
 * date           : 2024/04/08
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/04/08        GAMJA       최초 생성
 */
public class ObjectPoolTest {
    public static void main(String[] args ){
        ObjectPool<String> pool = new ObjectPool<>(String.class);

        try {
            String str = pool.borrowObject();
            String str2 = null;
            System.out.println(" str is poolist :"+pool.isValidateObject(str));
            System.out.println(" str2 is poolist :"+pool.isValidateObject(str2));
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
