package com.webperside.brogrammersspecialforum.annotations;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@ApiImplicitParams({
        @ApiImplicitParam(name = "page", defaultValue = "0", dataType = "int", paramType = "query"),
        @ApiImplicitParam(name = "size", defaultValue = "100", dataType = "int", paramType = "query")})
public @interface Pagination {

}


