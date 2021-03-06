/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.liferay.blade.upgrade.liferay70.cmds;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import aQute.lib.io.IO;

/**
 * @author Gregory Amerson
 */
public class CopyPortalSettingsCommandTest {

	final File dest = new File("generated/copyPortalSettings");
	final File src = new File("projects/copyPortalSettings");

	@Before
	public void cleanUpFiles() throws IOException {
		IO.delete(dest);
		Files.createDirectories(dest.toPath());
	}

	@Test
	public void copyOnlyInterestingPropertiesFiles() throws Exception {
		Object retval =
			new CopyPortalSettingsCommand().copyPortalSettings(src, dest);

		assertNull(retval);

		File[] expected = {
			new File(dest, "portal-ext.properties"),
			new File(dest, "portal-liferay.com.properties"),
			new File(dest, "portal-setup-wizard.properties"),
			new File(dest, "system-ext.properties"),
		};

		File[] actual = dest.listFiles();

		Arrays.sort(actual);

		for (int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], actual[i]);
		}
	}

	@Test
	public void pullParmetersFromMap() throws Exception {
		Map<String, File> parameters = new HashMap<>();
		parameters.put(CopyPortalSettingsCommand.PARAM_SOURCE, src);
		parameters.put(CopyPortalSettingsCommand.PARAM_DEST, dest);

		Object retval =
			new CopyPortalSettingsCommand().execute(parameters);

		assertNull(retval);

		File[] expected = {
			new File(dest, "portal-ext.properties"),
			new File(dest, "portal-liferay.com.properties"),
			new File(dest, "portal-setup-wizard.properties"),
			new File(dest, "system-ext.properties"),
		};

		File[] actual = dest.listFiles();

		Arrays.sort(actual);

		for (int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], actual[i]);
		}
	}

}
