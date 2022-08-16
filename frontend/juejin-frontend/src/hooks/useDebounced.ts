export default class Debounced {
    public use = (
        func: Function,
        delay: number,
        immediate: boolean = false
    ): Function => {
        let timer: NodeJS.Timeout;
        return (...args: any) => {
            if (immediate) {
                func.apply(this, args);
                immediate = false;
                return;
            }
            clearTimeout(timer);
            timer = setTimeout(() => {
                func.apply(this, args);
            }, delay);
        }
    }
}