import dayjs from 'dayjs';
import 'dayjs/locale/zh-cn'
import rTime from 'dayjs/plugin/relativeTime'
dayjs.locale('zh-cn')
dayjs.extend(rTime)
export default (date: string) => {
    return dayjs(date, 'yyyy-MM-dd').toNow(true) + '前'
}