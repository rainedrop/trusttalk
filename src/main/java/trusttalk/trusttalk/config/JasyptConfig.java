package trusttalk.trusttalk.config;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class JasyptConfig {

	private final static String ALHORITHM = "PBEWithMD5AndDES";
	private final static String CNT = "1000";
	private final static String POOL_SIZE = "1";
	private final static String BASE64 = "base64";

	@Value("${JASYPT_PASSWORD}")
	private String password;

	@Bean(name = "jasyptStringEncryptor")
	public StringEncryptor StringEncryptor() {
		SimpleStringPBEConfig config = new SimpleStringPBEConfig();

		config.setPassword(password); // 암호화할 때 사용하는 키
		config.setAlgorithm(ALHORITHM); // 암호화 알고리즘
		config.setKeyObtentionIterations(CNT); // 반복 해싱 횟수
		config.setPoolSize(POOL_SIZE); // 암호화 인스턴스 POOL
		config.setStringOutputType(BASE64); // 인코딩 방식

		PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
		encryptor.setConfig(config);

		return encryptor;
	}

}