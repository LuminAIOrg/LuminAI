export const fetchBackend = async <T>(
    path: string
) => {
    const res = await fetch(`${process.env.VUE_APP_BASE_URL}/${path}`);
    return (await res.json()) as T;
};
