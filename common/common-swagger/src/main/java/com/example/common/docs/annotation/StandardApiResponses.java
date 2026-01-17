package com.example.common.docs.annotation;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "成功",
                useReturnTypeSchema = true
        ),
    @ApiResponse(responseCode = "400", description = "参数校验失败",
            content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(
                            value = """
                                        {
                                            "code": 400,
                                            "message": "参数校验失败: xxxxx",
                                            "result": null
                                        }
                    """
                    )
            )
    ),
    @ApiResponse(responseCode = "401", description = "验证失败",
            content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(
                            value = """
                                        {
                                            "code": 401,
                                            "message": "验证失败",
                                            "result": null
                                        }
                    """
                    )
            )
    ),
    @ApiResponse(responseCode = "403", description = "无权限访问",
        content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                        value = """
                                    {
                                        "code": 403,
                                        "message": "当前用户没有权限访问",
                                        "result": null
                                    }
                """
                )
        )
    ),
    @ApiResponse(responseCode = "404", description = "数据不存在",
        content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                        value = """
                                    {
                                        "code": 404,
                                        "message": "数据不存在",
                                        "result": null
                                    }
                """
                )
        )
    ),
    @ApiResponse(responseCode = "409", description = "数据冲突，可能是以下情况："+ """
            
            资源已存在 
            
            版本冲突 例如：数据已被其他用户修改
            
            当前状态不允许操作 例如：订单已完成，无法取消
            
            并发任务冲突 例如：已有任务已在运行，无法启动新任务
            
            业务规则冲突，例如：不能重复购买
            
            依赖关系冲突 例如：该分类下还有商品，无法删除
            """,

            content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(
                            value = """
                                        {
                                            "code": 409,
                                            "message": "数据冲突",
                                            "result": null
                                        }
                    """
                    )
            )
    ),
    @ApiResponse(responseCode = "500", description = "服务器错误",
            content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(
                            value = """
                                        {
                                            "code": 500,
                                            "message": "服务器错误",
                                            "result": null
                                        }
                    """
                    )
            )
    )
})
public @interface StandardApiResponses {
}
