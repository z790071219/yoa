package com.yoa;

import com.yoa.util.Global;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import java.util.*;

public class encryption {
    public static void main(String[] args) {//653f2ee4531e2f78800925e6d9d9d3ff
        System.out.println(new SimpleHash(Global.HASH_ALGORITHM,"123",ByteSource.Util.bytes("张三"),Global.HASH_INTERATIONS).toString()
);
        System.out.println(ByteSource.Util.bytes("张三"));
    }

}
