package com.biswamit.springboot.jpa.rest.exception;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public enum JpaRestErrorCode {

    //Invalid input error codes
    ValidationFailed(1001, FailureType.InvalidInput, "Validation failed."),
    EmptyEmployeeAID(1002, FailureType.InvalidInput, "Employee autodId cannot be null or empty."),
    EmptyEmployeeUUID(1002, FailureType.InvalidInput, "Employee employeeId cannot be null or empty."),
    EmptyEmployeeFName(1002, FailureType.InvalidInput, "Employee first name cannot be null or empty."),
    EmptyEmployeeLName(1003, FailureType.InvalidInput, "Employee last name cannot be null or empty."),

    //Event related error codes
    InvalidEmployee(404, FailureType.ResourceNotFound, "Employee with id %s not found."),
    EmployeeNotFound(404, FailureType.ResourceNotFound, "No employee found."),
    AddressNotFound(404, FailureType.ResourceNotFound, "No address found."),


    //Generic
    InvalidMethodArgument(400, FailureType.InvalidInput, "Invalid Method argument."),
    InvalidHttpMessage(400, FailureType.InvalidInput, "Http message not readable."),
    InvalidRequest(400, FailureType.BadRequest, "Bad request."),

    //Generic-400
    DefaultException(400, FailureType.GenericError, "Error while processing the request"),

    //Generic-409
    InvalidDataAccessApiUsage(409, FailureType.InvalidApiUsage, "Invalid Api usage."),

    //Generic-500
    InternalServerError(500, FailureType.ServerError, "Internal server error.");


    //failure type
    public enum FailureType {
        Metadata, BeanInitialization, InvalidConfiguration,
        Security,
        InvalidInput, ResourceNotFound, InvalidApiUsage, BadRequest,
        ServerError, GenericError, Other
    }

    private static Map<Integer, JpaRestErrorCode> lookup = new HashMap<>();

    /** set the lookup map */
    static {
        Stream.of(JpaRestErrorCode.values())
                .forEach(iotErrorCode -> lookup.put(iotErrorCode.code, iotErrorCode));
    }

    private final int code;
    private final FailureType failureType;
    private final String message;

    JpaRestErrorCode(int code, FailureType failureType, String message) {
        this.code = code;
        this.failureType = failureType;
        this.message = message;
    }

    public String getMessage() {
        return "[" + code + "] " + message;
    }

    public int getCode() {
        return code;
    }

    public static JpaRestErrorCode getCode(Integer code) {
        return Optional.ofNullable(lookup.get(code)).orElse(DefaultException);
    }

    public FailureType getFailureType() {
        return failureType;
    }

}
