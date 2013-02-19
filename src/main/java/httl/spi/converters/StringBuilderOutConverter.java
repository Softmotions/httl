/*
 * Copyright 2011-2012 HTTL Team.
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package httl.spi.converters;

import httl.spi.Converter;
import httl.internal.util.UnsafeStringWriter;

import java.io.IOException;
import java.io.Writer;
import java.text.ParseException;

/**
 * StringBuilderOutConverter. (SPI, Singleton, ThreadSafe)
 * 
 * @see httl.spi.parsers.AbstractParser#setOutConverter(Converter)
 * 
 * @author Liang Fei (liangfei0201 AT gmail DOT com)
 */
public class StringBuilderOutConverter implements Converter<StringBuilder, Writer> {

	public Writer convert(StringBuilder value) throws IOException, ParseException {
		return new UnsafeStringWriter(value);
	}

}
