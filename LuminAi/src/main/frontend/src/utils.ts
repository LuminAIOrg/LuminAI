export const fetchBackend = async <T>(
    path: string
) => {
    const res = await fetch(`${process.env.VUE_APP_BASE_URL}/${path}`);
    return (await res.json()) as T;
};

export const fetchBackendText = async (
    path: string
) => {
    const res = await fetch(`${process.env.VUE_APP_BASE_URL}/${path}`);
    return (await res.text()) as string;
};


/**
 * @type {T: any} return type
 * @Type {Z: any} body type
 * @param path
 * @param body
 */
export const postBackend = async <T, Z>(
    path: string,
    body: Z,
    returnResponse?: boolean
)=> {
    const res = await fetch(`${process.env.VUE_APP_BASE_URL}/${path}`, {
        headers:{
            "Content-Type": "application/json"
        },
        method:"POST",
        body: JSON.stringify(body)
    });
    if (returnResponse) {
        return res
    }
    return (await res.json()) as T
}
