/*
 * Copyright (C) 2016 Gregory DEPUILLE
 *
 * Licensed either under the Apache License, Version 2.0, or (at your option)
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation (subject to the "Classpath" exception),
 * either version 2, or any later version (collectively, the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *     http://www.gnu.org/licenses/
 *     http://www.gnu.org/software/classpath/license.html
 *
 * or as provided in the LICENSE.txt file that accompanied this code.
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.bytedeco.javacpp.presets;

import org.bytedeco.javacpp.annotation.Platform;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.tools.Info;
import org.bytedeco.javacpp.tools.InfoMap;
import org.bytedeco.javacpp.tools.InfoMapper;

/**
 * @author Gregory DEPUILLE
 */
@Properties(target="org.bytedeco.javacpp.rplidar", value={
  @Platform(include={"<rplidar.h>", "<rplidar_cmd.h>", "<rplidar_driver.h>", "<rplidar_protocol.h>", "<rptypes.h>"})
})
public class rplidar implements InfoMapper {
    public void map(InfoMap infoMap) {
        infoMap
            .put(new Info("_s8").valueTypes("byte").pointerTypes("BytePointer", "ByteBuffer", "byte[]"))
            .put(new Info("_u8").cast().valueTypes("byte").pointerTypes("IntPointer", "IntBuffer", "int[]"))

            .put(new Info("_s16").valueTypes("short").pointerTypes("ShortPointer", "ShortBuffer", "short[]"))
            .put(new Info("_u16").cast().valueTypes("short").pointerTypes("ShortPointer", "ShortBuffer", "short[]"))

            .put(new Info("_s32").valueTypes("int").pointerTypes("IntPointer", "IntBuffer", "int[]"))
            .put(new Info("_u32", "u_result").cast().valueTypes("int").pointerTypes("IntPointer", "IntBuffer", "int[]"))

            .put(new Info("_s64", "_u64").cast().valueTypes("long").pointerTypes("LongPointer", "LongBuffer", "long[]"));
    }
}
