package com.example.aoplibrary;

import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.io.File;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
* Created by HSQ on 2017/5/25.
*/
@Aspect
public class AOP {
private static final String POINTCUT_METHOD = "execution(* tk.woppo.mgame..*.*(..))";
private static int num = 0, times = 0;
private static Stack<String> stack = new Stack<String>();	                    /* 函数调用栈 */
private static Map<String, Integer> cnt = new HashMap<String, Integer>();
private static Map<String, Integer> countMap = new HashMap<String, Integer>();  /* 函数调用次数计数 */
private static StringBuilder dot = new StringBuilder("\r\n");                   /* 字符串记录图 */
private static String sFilePath = "/sdcard/";
private static final String FLITER_STR = "tk.woppo.mgame.";            /* 过滤长类名 */
private static boolean vis[][] = new boolean[1005][1005];
private static int flag = 0, flag1 = 0;


@Pointcut(POINTCUT_METHOD)
public synchronized void methodAnnotatedWithDebugTrace() {
}

//    @Before("methodAnnotatedWithDebugTrace()")
//    public synchronized void before(JoinPoint joinPoint){
//        //push stack,print the top of the stack with current function
//    }
//    @After("methodAnnotatedWithDebugTrace()")
//    public synchronized void after(JoinPoint joinPoint){
//        //pop stack
//    }

@Before("methodAnnotatedWithDebugTrace()")
public synchronized void before(JoinPoint joinPoint){
    MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
    String signatureStr ="\"" + methodSignature.toString().replace(FLITER_STR,"")+ "\"";
    times++;
    if(countMap.containsKey(signatureStr)){
        cnt.put(signatureStr, cnt.get(signatureStr)+1);
    }
    else{
        countMap.put(signatureStr,num);
        cnt.put(signatureStr, 1);
        num++;
    }

    if(stack.size() == 0){                                                  /* 若为根节点 */
        dot.append(signatureStr +"\r\n");
    }
    else{
        Log.d("AOP", stack.peek() + "->" + signatureStr);                   /* 不为根节点 */
        dot.append(stack.peek() + "->" + signatureStr);
        int x = countMap.get(stack.peek());
        int y = countMap.get(signatureStr);
        if(vis[x][y] == false){
            vis[x][y] = true;
            Log.d("AOP", stack.peek() + "->" + signatureStr);
            writeTreeFile(dot.toString());
        }
    }

    if(times % 1000 == 0){
        writeTimeFile(cnt);
    }
    dot = new StringBuilder("\r\n");
    stack.push(signatureStr);
}

@After("methodAnnotatedWithDebugTrace()")
public synchronized void after(JoinPoint joinPoint){
    MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
    String signatureStr ="\"" + methodSignature.toString().replace(FLITER_STR,"")+ "\"";

    stack.pop();
}


synchronized void writeTreeFile(String dotStr) {
    String dotFile = sFilePath + "AOP.txt";
    try {
        File file = new File(dotFile);
        if (!file.exists()) {
            Log.d("AOP", "Create the file:" + dotFile);
            file.createNewFile();
        }

        if(flag == 0){
            FileOutputStream out = null;
            out = new FileOutputStream(file);
            out.write(dotStr.getBytes());
            out.flush();
            flag = 1;
        }
        else{
            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            raf.seek(file.length());
            raf.write(dotStr.getBytes());
            raf.close();
        }

        Log.d("AOP", "correct");
    } catch (Exception e) {
        Log.d("AOP", e.toString());
        Log.e("AOP", "Error on write File.");
    }
}
synchronized void writeTimeFile(Map<String, Integer> countMap) {
    String dotFile = sFilePath + "CNT.txt";
    try {
        File file = new File(dotFile);
        if (!file.exists()) {
            Log.d("AOP", "Create the file:" + dotFile);
            file.createNewFile();
        }
        if(flag1 == 0){
            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            if(!countMap.isEmpty()){
                for(Map.Entry<String, Integer> entry : countMap.entrySet()){
                    raf.write((entry.toString()+ "\r\n").getBytes());
                }
            }
            raf.write(("\r\n\r\n").getBytes());
            raf.close();
            flag1 = 1;
        }
        else{
            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            raf.seek(file.length());
            if(!countMap.isEmpty()){
                for(Map.Entry<String, Integer> entry : countMap.entrySet()){
                    raf.write((entry.toString()+ "\r\n").getBytes());
                }
            }
            raf.write(("\r\n\r\n").getBytes());
            raf.close();
        }

//            RandomAccessFile raf = new RandomAccessFile(file, "rw");
//            if(!countMap.isEmpty()){
//                for(Map.Entry<String, Integer> entry : countMap.entrySet()){
//                    raf.write((entry.toString()+ "\r\n").getBytes());
//                }
//            }
//            raf.close();
    } catch (Exception e) {
        Log.e("AOP", "Error on write File.");
    }
}
}
