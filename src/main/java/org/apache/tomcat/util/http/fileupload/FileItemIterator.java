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
package org.apache.tomcat.util.http.fileupload;

import java.io.IOException;

/**
 * An iterator, as returned by
 * {@link org.apache.tomcat.util.http.fileupload.FileUploadBase#getItemIterator(org.apache.tomcat.util.http.fileupload.RequestContext)}.
 */
public interface FileItemIterator {

	/**
	 * Returns, whether another instance of {@link org.apache.tomcat.util.http.fileupload.FileItemStream}
	 * is available.
	 *
	 * @return True, if one or more additional file items
	 * are available, otherwise false.
	 * @throws FileUploadException Parsing or processing the
	 *                             file item failed.
	 * @throws java.io.IOException Reading the file item failed.
	 */
	boolean hasNext() throws FileUploadException, IOException;

	/**
	 * Returns the next available {@link org.apache.tomcat.util.http.fileupload.FileItemStream}.
	 *
	 * @return FileItemStream instance, which provides
	 * access to the next file item.
	 * @throws java.util.NoSuchElementException No more items are available. Use
	 *                                          {@link #hasNext()} to prevent this exception.
	 * @throws FileUploadException              Parsing or processing the
	 *                                          file item failed.
	 * @throws java.io.IOException              Reading the file item failed.
	 */
	FileItemStream next() throws FileUploadException, IOException;

}
