package net.ibebu.user.common.data.enums

import net.ibebu.user.core.base.BaseEnum
import net.ibebu.user.core.base.BaseResponsePopup


enum class FailMessageEnums(val message: String, val title: String, val content: String) :
    BaseEnum<FailMessageEnums> {
    FAIL_LOGIN("Invalid account", "Wrong account", "Please verify your account information again."),
    FAIL_ALERT("A confirmation window pops up", "Alert", "A confirmation window pops up"),
    FAIL_DELETE("Failed to delete", "Error", "Failed to delete"),
    FAIL_INSERT("Failed to insert", "Error", "Failed to insert"),
    FAIL_UPDATE("Failed to update", "Error", "Failed to update"),
    EMPTY_DIVISION_NAME("Please, insert division name", "Error", "Please, insert division name"),
    EMPTY_DIVISION_DESC("Please, insert division description", "Error", "Please, insert division description"),
    DELETE_COMMENT("This comment has been deleted", "Delete", "This comment has been deleted"),
    FAIL_EMPLOYEE_FIND("The user does not exist", "This is a server error", "The user does not exist"),
    FAIL_SEVER_ERROR("Internal server error", "Internal server error", "Internal server error"),
    EMPTY_LIST("The list is empty.", "Error", "The list is empty."),
    EMPTY_DATA("The data is empty.", "Error", "The data is empty."),
    EMPTY_BRAND_NAME("Please, insert brand name", "Error", "Please, insert brand name"),
    EMPTY_BRAND_DESC("Please, insert brand description", "Error", "Please, insert brand description"),
    NOT_FOUND_BRAND("The brand does not exist", "This is a server error", "The brand does not exist"),
    NOT_FOUND_DIVISION("The division does not exist", "This is a server error", "The division does not exist"),
    EXISTS_LOGIN_ID("Login ID that already exists.", "Warning", "Login ID that already exists."),
    EMPTY_CATEGORY_NAME("Please, insert category name", "Error", "Please, insert category name"),


    ;


    override val value = this

    fun toPopupMessage(): BaseResponsePopup.PopupMessage {
        return BaseResponsePopup.PopupMessage(
            popType = PopupTypeEnum.ALERT,
            popTit = this.title,
            popCntn = this.content
        )
    }
}