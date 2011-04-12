/** Copyright (c) 2008-2011, Brooklyn eXperimental Media Center
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of Brooklyn eXperimental Media Center nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL Brooklyn eXperimental Media Center BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package edu.poly.bxmc.betaville.server.authentication;

/**
 * Allows restriction to be placed on the usernames that can
 * be created.
 * @author Skye Book
 *
 */
public class InputRequirement{

	public enum MatchingMode{
		WHOLE,
		CONTAINS
	}

	public enum TextMode{
		PLAIN,
		REGEX
	}

	private String requirementString;
	private int minLengthRequirement=-1;
	private MatchingMode matchingMode;
	private TextMode textMode;

	/**
	 * 
	 */
	public InputRequirement(String requirementString, int minLengthRequirement, MatchingMode matchingMode, TextMode textMode){
		this.matchingMode=matchingMode;
		this.minLengthRequirement=minLengthRequirement;
		this.textMode=textMode;
		this.requirementString=requirementString;
	}

	public boolean validate(String content){
		switch (matchingMode) {
		case WHOLE:
			switch (textMode) {
			case PLAIN:
				return (!content.equals(requirementString) && lengthIsValid(content.length()));
			case REGEX:
				return (!content.matches(requirementString) && lengthIsValid(content.length()));
			}
			break;

		case CONTAINS:
			switch (textMode) {
			case PLAIN:
				return (!content.equals(requirementString) && lengthIsValid(content.length()));
			case REGEX:
				return (!content.matches(requirementString) && lengthIsValid(content.length()));
			}
			break;
		}
		return true;
	}

	private boolean lengthIsValid(int contentLength){
		return (contentLength>minLengthRequirement || contentLength*minLengthRequirement<=0);
	}
}
