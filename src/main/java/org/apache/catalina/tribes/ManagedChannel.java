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
package org.apache.catalina.tribes;

import java.util.Iterator;

/**
 * Channel interface
 * A managed channel interface gives you access to the components of the channels
 * such as senders, receivers, interceptors etc for configurations purposes
 *
 * @author Filip Hanik
 */
public interface ManagedChannel extends Channel {

	/**
	 * returns the channel sender
	 *
	 * @return ChannelSender
	 * @see org.apache.catalina.tribes.ChannelSender
	 */
	public ChannelSender getChannelSender();

	/**
	 * Sets the channel sender
	 *
	 * @param sender ChannelSender
	 * @see org.apache.catalina.tribes.ChannelSender
	 */
	public void setChannelSender(ChannelSender sender);

	/**
	 * returns the channel receiver
	 *
	 * @return ChannelReceiver
	 * @see ChannelReceiver
	 */
	public ChannelReceiver getChannelReceiver();

	/**
	 * Sets the channel receiver
	 *
	 * @param receiver ChannelReceiver
	 * @see ChannelReceiver
	 */
	public void setChannelReceiver(ChannelReceiver receiver);

	/**
	 * Returns the membership service
	 *
	 * @return MembershipService
	 * @see MembershipService
	 */
	public MembershipService getMembershipService();

	/**
	 * Sets the membership service
	 *
	 * @param service MembershipService
	 * @see MembershipService
	 */
	public void setMembershipService(MembershipService service);

	/**
	 * Returns the interceptor stack
	 *
	 * @return Iterator
	 * @see org.apache.catalina.tribes.Channel#addInterceptor(ChannelInterceptor)
	 */
	public Iterator<ChannelInterceptor> getInterceptors();
}
