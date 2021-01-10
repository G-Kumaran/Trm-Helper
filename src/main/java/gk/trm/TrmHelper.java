package gk.trm;

import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class TrmHelper
{
	public static void main(String[] args)
	{
		new SpringApplicationBuilder(TrmHelper.class)   .bannerMode(Banner.Mode.OFF)
														.web(WebApplicationType.SERVLET)
														.registerShutdownHook(true)
														.build()
														.run(args);
		log.warn("Initiation : Success");
	}

}
