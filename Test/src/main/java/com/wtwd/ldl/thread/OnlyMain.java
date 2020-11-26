package com.wtwd.ldl.thread;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * @Author ldaoliang
 * @Date 2019/9/30 0030 下午 1:49
 * @Description TODO
 **/
public class OnlyMain {
		public static void main(String[] args) {
		//虚拟机线程管理的接口
		ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
		ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false,false);

		for(ThreadInfo threadInfo : threadInfos){
			System.out.println("["+threadInfo.getThreadId()+"] ["+threadInfo.getThreadName()+"]");
		}

	}
}

