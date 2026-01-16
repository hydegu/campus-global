package com.example.common.file;

import com.example.common.file.core.FileProperties;
import com.example.common.file.local.LocalFileAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

@Import({ LocalFileAutoConfiguration.class })
@EnableConfigurationProperties({ FileProperties.class })
public class FileAutoConfiguration {

}
