/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.tomcat.util.net;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/**
 * Properties that can be set in the &lt;Connector&gt; element
 * in server.xml. All properties are prefixed with &quot;socket.&quot;
 * and are currently only working for the Nio connector
 *
 * @author Filip Hanik
 */
public class SocketProperties {
	/**
	 * Enable/disable key cache, this bounded cache stores
	 * KeyAttachment objects to reduce GC
	 * Default is 500
	 * -1 is unlimited
	 * 0 is disabled
	 */
	protected int keyCache = 500;

	/**
	 * Enable/disable socket processor cache, this bounded cache stores
	 * SocketProcessor objects to reduce GC
	 * Default is 500
	 * -1 is unlimited
	 * 0 is disabled
	 */
	protected int processorCache = 500;

	/**
	 * Enable/disable poller event cache, this bounded cache stores
	 * PollerEvent objects to reduce GC for the poller
	 * Default is 500
	 * -1 is unlimited
	 * 0 is disabled
	 * >0 the max number of objects to keep in cache.
	 */
	protected int eventCache = 500;

	/**
	 * Enable/disable direct buffers for the network buffers
	 * Default value is enabled
	 */
	protected boolean directBuffer = false;

	/**
	 * Socket receive buffer size in bytes (SO_RCVBUF).
	 * JVM default used if not set.
	 */
	protected Integer rxBufSize = null;

	/**
	 * Socket send buffer size in bytes (SO_SNDBUF).
	 * JVM default used if not set.
	 */
	protected Integer txBufSize = null;

	/**
	 * The application read buffer size in bytes.
	 * Default value is rxBufSize
	 */
	protected int appReadBufSize = 8192;

	/**
	 * The application write buffer size in bytes
	 * Default value is txBufSize
	 */
	protected int appWriteBufSize = 8192;

	/**
	 * NioChannel pool size for the endpoint,
	 * this value is how many channels
	 * -1 means unlimited cached, 0 means no cache
	 * Default value is 500
	 */
	protected int bufferPool = 500;

	/**
	 * Buffer pool size in bytes to be cached
	 * -1 means unlimited, 0 means no cache
	 * Default value is 100MB (1024*1024*100 bytes)
	 */
	protected int bufferPoolSize = 1024 * 1024 * 100;

	/**
	 * TCP_NO_DELAY option. JVM default used if not set.
	 */
	protected Boolean tcpNoDelay = Boolean.TRUE;

	/**
	 * SO_KEEPALIVE option. JVM default used if not set.
	 */
	protected Boolean soKeepAlive = null;

	/**
	 * OOBINLINE option. JVM default used if not set.
	 */
	protected Boolean ooBInline = null;

	/**
	 * SO_REUSEADDR option. JVM default used if not set.
	 */
	protected Boolean soReuseAddress = null;

	/**
	 * SO_LINGER option, paired with the <code>soLingerTime</code> value.
	 * JVM defaults used unless both attributes are set.
	 */
	protected Boolean soLingerOn = null;

	/**
	 * SO_LINGER option, paired with the <code>soLingerOn</code> value.
	 * JVM defaults used unless both attributes are set.
	 */
	protected Integer soLingerTime = null;

	/**
	 * SO_TIMEOUT option. default is 20000.
	 */
	protected Integer soTimeout = new Integer(20000);

	/**
	 * Performance preferences according to
	 * http://docs.oracle.com/javase/1.5.0/docs/api/java/net/Socket.html#setPerformancePreferences(int,%20int,%20int)
	 * All three performance attributes must be set or the JVM defaults will be
	 * used.
	 */
	protected Integer performanceConnectionTime = null;

	/**
	 * Performance preferences according to
	 * http://docs.oracle.com/javase/1.5.0/docs/api/java/net/Socket.html#setPerformancePreferences(int,%20int,%20int)
	 * All three performance attributes must be set or the JVM defaults will be
	 * used.
	 */
	protected Integer performanceLatency = null;

	/**
	 * Performance preferences according to
	 * http://docs.oracle.com/javase/1.5.0/docs/api/java/net/Socket.html#setPerformancePreferences(int,%20int,%20int)
	 * All three performance attributes must be set or the JVM defaults will be
	 * used.
	 */
	protected Integer performanceBandwidth = null;

	/**
	 * The minimum frequency of the timeout interval to avoid excess load from
	 * the poller during high traffic
	 */
	protected long timeoutInterval = 1000;

	/**
	 * Timeout in milliseconds for an unlock to take place.
	 */
	protected int unlockTimeout = 250;

	public void setProperties(Socket socket) throws SocketException {
		if (rxBufSize != null)
			socket.setReceiveBufferSize(rxBufSize.intValue());
		if (txBufSize != null)
			socket.setSendBufferSize(txBufSize.intValue());
		if (ooBInline != null)
			socket.setOOBInline(ooBInline.booleanValue());
		if (soKeepAlive != null)
			socket.setKeepAlive(soKeepAlive.booleanValue());
		if (performanceConnectionTime != null && performanceLatency != null &&
				performanceBandwidth != null)
			socket.setPerformancePreferences(
					performanceConnectionTime.intValue(),
					performanceLatency.intValue(),
					performanceBandwidth.intValue());
		if (soReuseAddress != null)
			socket.setReuseAddress(soReuseAddress.booleanValue());
		if (soLingerOn != null && soLingerTime != null)
			socket.setSoLinger(soLingerOn.booleanValue(),
					soLingerTime.intValue());
		if (soTimeout != null && soTimeout.intValue() >= 0)
			socket.setSoTimeout(soTimeout.intValue());
		if (tcpNoDelay != null)
			socket.setTcpNoDelay(tcpNoDelay.booleanValue());
	}

	public void setProperties(ServerSocket socket) throws SocketException {
		if (rxBufSize != null)
			socket.setReceiveBufferSize(rxBufSize.intValue());
		if (performanceConnectionTime != null && performanceLatency != null &&
				performanceBandwidth != null)
			socket.setPerformancePreferences(
					performanceConnectionTime.intValue(),
					performanceLatency.intValue(),
					performanceBandwidth.intValue());
		if (soReuseAddress != null)
			socket.setReuseAddress(soReuseAddress.booleanValue());
		if (soTimeout != null && soTimeout.intValue() >= 0)
			socket.setSoTimeout(soTimeout.intValue());
	}


	public boolean getDirectBuffer() {
		return directBuffer;
	}

	public void setDirectBuffer(boolean directBuffer) {
		this.directBuffer = directBuffer;
	}

	public boolean getOoBInline() {
		return ooBInline.booleanValue();
	}

	public void setOoBInline(boolean ooBInline) {
		this.ooBInline = Boolean.valueOf(ooBInline);
	}

	public int getPerformanceBandwidth() {
		return performanceBandwidth.intValue();
	}

	public void setPerformanceBandwidth(int performanceBandwidth) {
		this.performanceBandwidth = Integer.valueOf(performanceBandwidth);
	}

	public int getPerformanceConnectionTime() {
		return performanceConnectionTime.intValue();
	}

	public void setPerformanceConnectionTime(int performanceConnectionTime) {
		this.performanceConnectionTime =
				Integer.valueOf(performanceConnectionTime);
	}

	public int getPerformanceLatency() {
		return performanceLatency.intValue();
	}

	public void setPerformanceLatency(int performanceLatency) {
		this.performanceLatency = Integer.valueOf(performanceLatency);
	}

	public int getRxBufSize() {
		return rxBufSize.intValue();
	}

	public void setRxBufSize(int rxBufSize) {
		this.rxBufSize = Integer.valueOf(rxBufSize);
	}

	public boolean getSoKeepAlive() {
		return soKeepAlive.booleanValue();
	}

	public void setSoKeepAlive(boolean soKeepAlive) {
		this.soKeepAlive = Boolean.valueOf(soKeepAlive);
	}

	public boolean getSoLingerOn() {
		return soLingerOn.booleanValue();
	}

	public void setSoLingerOn(boolean soLingerOn) {
		this.soLingerOn = Boolean.valueOf(soLingerOn);
	}

	public int getSoLingerTime() {
		return soLingerTime.intValue();
	}

	public void setSoLingerTime(int soLingerTime) {
		this.soLingerTime = Integer.valueOf(soLingerTime);
	}

	public boolean getSoReuseAddress() {
		return soReuseAddress.booleanValue();
	}

	public void setSoReuseAddress(boolean soReuseAddress) {
		this.soReuseAddress = Boolean.valueOf(soReuseAddress);
	}

	public int getSoTimeout() {
		return soTimeout.intValue();
	}

	public void setSoTimeout(int soTimeout) {
		this.soTimeout = Integer.valueOf(soTimeout);
	}

	public boolean getTcpNoDelay() {
		return tcpNoDelay.booleanValue();
	}

	public void setTcpNoDelay(boolean tcpNoDelay) {
		this.tcpNoDelay = Boolean.valueOf(tcpNoDelay);
	}

	public int getTxBufSize() {
		return txBufSize.intValue();
	}

	public void setTxBufSize(int txBufSize) {
		this.txBufSize = Integer.valueOf(txBufSize);
	}

	public int getBufferPool() {
		return bufferPool;
	}

	public void setBufferPool(int bufferPool) {
		this.bufferPool = bufferPool;
	}

	public int getBufferPoolSize() {
		return bufferPoolSize;
	}

	public void setBufferPoolSize(int bufferPoolSize) {
		this.bufferPoolSize = bufferPoolSize;
	}

	public int getEventCache() {
		return eventCache;
	}

	public void setEventCache(int eventCache) {
		this.eventCache = eventCache;
	}

	public int getKeyCache() {
		return keyCache;
	}

	public void setKeyCache(int keyCache) {
		this.keyCache = keyCache;
	}

	public int getAppReadBufSize() {
		return appReadBufSize;
	}

	public void setAppReadBufSize(int appReadBufSize) {
		this.appReadBufSize = appReadBufSize;
	}

	public int getAppWriteBufSize() {
		return appWriteBufSize;
	}

	public void setAppWriteBufSize(int appWriteBufSize) {
		this.appWriteBufSize = appWriteBufSize;
	}

	public int getProcessorCache() {
		return processorCache;
	}

	public void setProcessorCache(int processorCache) {
		this.processorCache = processorCache;
	}

	public long getTimeoutInterval() {
		return timeoutInterval;
	}

	public void setTimeoutInterval(long timeoutInterval) {
		this.timeoutInterval = timeoutInterval;
	}

	public int getDirectBufferPool() {
		return bufferPool;
	}

	public void setDirectBufferPool(int directBufferPool) {
		this.bufferPool = directBufferPool;
	}

	public int getUnlockTimeout() {
		return unlockTimeout;
	}

	public void setUnlockTimeout(int unlockTimeout) {
		this.unlockTimeout = unlockTimeout;
	}


}
