import React  from "react";
import {AutocompleteInput, ReferenceInput, SelectInput, Validator } from "react-admin";

type AdminReferenceInputProps = {
    source: string;
    reference: string;
    allowEmpty?: boolean;
    perPage?: number;
    optionText?: string;
    validate?: Validator | Validator[]
}

export function AdminReferenceInput({optionText = "name", ...rest}: AdminReferenceInputProps) {
    return (
        <ReferenceInput {...rest}>
            <SelectInput optionText={optionText} />
        </ReferenceInput>
    )
}

export function AdminRefetenceAutocompleteInput({optionText = "name", ...rest}: AdminReferenceInputProps) {
    return (
        <ReferenceInput {...rest}>
            <AutocompleteInput optionText={optionText}
                               options={{
                                    InputProps: {
                                        fullWidth: true,
                                    },
                               }}
            />
        </ReferenceInput>
    )
}
