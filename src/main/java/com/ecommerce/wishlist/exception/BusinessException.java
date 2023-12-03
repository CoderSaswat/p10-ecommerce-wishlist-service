package com.ecommerce.wishlist.exception;

import com.ecommerce.wishlist.enums.ResponseCodes;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
  private final ResponseCodes code;

  public BusinessException( final String reason ) {
    this( reason, null );
  }

  public BusinessException( final String reason, final ResponseCodes code ) {
    super( reason );
    this.code = code;
  }

}