package com.gentleninja.exceptions;

import java.time.LocalDateTime;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponse {
  private LocalDateTime timestamp;
  private int status;
  private String error;
  private String path;
}
