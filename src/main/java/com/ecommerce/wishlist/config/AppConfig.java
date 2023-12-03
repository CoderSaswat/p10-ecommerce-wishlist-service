package com.ecommerce.wishlist.config;

import com.ecommerce.wishlist.mapper.PatchMapper;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }


  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

  @Bean
  public PatchMapper patchMapper() {
    PatchMapper patchMapper = new PatchMapper();
    patchMapper.getConfiguration().setMatchingStrategy( MatchingStrategies.STRICT )
            .setPropertyCondition( Conditions.isNotNull() );
    return patchMapper;
  }


}
